from flask import Blueprint, request, jsonify

from app.model.request.campaña import CampañaRequestModel, UnirmeACampaña

from pydantic import ValidationError

from app.utils.session import user_required

from app.model.sql.model.user import User
from app.model.sql.model.campaña import Campaña

from app.controllers.campaña import Campaña_controller

from app.model.sql.dao.user import UserDAO
from app.model.sql.dao.ficha import FichaDAO
from app.model.sql.dao.usuarioCampaña import UsuarioCampañaDAO
from app.model.sql.dao.campaña import CampañaDAO

campaña = Blueprint("campaña", __name__, url_prefix="/campanyan")

@campaña.get("")
@user_required
def campaña_list(user: User):
    manual_controller: Campaña_controller = Campaña_controller()
    
    count: int
    camapña: list[dict]
    count, camapña = manual_controller.get_all_camapaña()
    
    if(count == 0):
        return {"error": "sorry there are no camapña available right now"}, 404
    else:
        return {"kuantos": count, "datos": camapña}, 200

@campaña.get("/mine")
@user_required
def campaña_list_p(user: User):
    manual_controller: Campaña_controller = Campaña_controller()
    
    count: int
    camapña: list[dict]
    count, camapña = manual_controller.get_all_camapaña_for_user(user_id=user.idUsuario)
    
    if(count == 0):
        return {"error": "fuck you, no campaña for u >:("}, 404
    else:
        return {"kuantos": count, "datos": camapña}, 200

@campaña.get("/<id_campanyan>")
@user_required
def get_manual_info(id_campanyan: str, user: User):
    id: str
    try:
        id = int(id_campanyan)
    except Exception as e:
        return {"error": "minusvalid id value"}, 422
    
    manual_controller: Campaña_controller = Campaña_controller()
    
    campaña: Campaña = manual_controller.get_camaña(id)
    
    if(campaña == None):
        return {"error": f"camamumui {id} cannot be found"}, 404
    else:
        return campaña, 200
    
@campaña.get("/<id_campanyan>/fichas")
@user_required
def get_fichardas(id_campanyan: str, user: User):
    id: str
    try:
        id = int(id_campanyan)
    except Exception as e:
        return {"error": "minusvalid id value"}, 422
    
    emanuel_dio_descontroller = UsuarioCampañaDAO()
    datardos = emanuel_dio_descontroller.get_all_by_campaña(id_campanyan)
    
    paco_dao = UserDAO()
    
    based_datardos = [{"ficha_id": microplastik.Ficha_TheStrange_id, "user_id": microplastik.Usuario_idUsuario, "username": paco_dao.get_by_id(microplastik.Usuario_idUsuario).nombre} for microplastik in datardos if microplastik.Ficha_TheStrange_id != None]
    
    if(len(based_datardos) == 0):
        return {"error": f"no fichas lailo, todavia"}, 404
    else:
        return based_datardos, 200
    
@campaña.post("/<id_campanyan>/fichas/<id_ficha>")
@user_required
def anyadir_ficharda(id_campanyan: str, id_ficha: str, user: User):
    id: str
    try:
        id = int(id_campanyan)
    except Exception as e:
        return {"error": "minusvalid id value"}, 422
    try:
        ficha = int(id_ficha)
    except Exception as e:
        return {"error": "minusvalid ficha id value"}, 422
    
    emanuel_dio_descontroller = UsuarioCampañaDAO()
    datardo = emanuel_dio_descontroller.get_by_id(user.idUsuario, id)
    
    if(datardo == None):
        return {"error": "no lailo"}, 403
    
    le_fichador = FichaDAO()
    fichardo = le_fichador.get_by_id(user.idUsuario, ficha)
    if(fichardo == None):
        return {"error": f"no ficha with id {ficha}"}, 404
    
    try:
        result = emanuel_dio_descontroller.add_ficharda(user.idUsuario, id, ficha)
        return result.to_json(), 200
    except Exception as e:
        return {"error", f"sabe dios: {str(e)}"}, 500

@campaña.post("/<id_campanyan>/archivar")
@user_required
def archive(id_campanyan: str, user: User):
    id: str
    try:
        id = int(id_campanyan)
    except Exception as e:
        return {"error": "minusvalid id value"}, 422
    
    manual_controller: Campaña_controller = Campaña_controller()
    
    archivada = manual_controller.archivar(id)
    
    if(archivada):
        return {"success": "archived"}, 200
    else:
        return {"error": "could not archive"}, 500

@campaña.post("/<id_campanyan>/desarchivar")
@user_required
def unarchive(id_campanyan: str, user: User):
    id: str
    try:
        id = int(id_campanyan)
    except Exception as e:
        return {"error": "minusvalid id value"}, 422
    
    manual_controller: Campaña_controller = Campaña_controller()
    
    archivada = manual_controller.desarchivar(id)
    
    if(archivada):
        return {"success": "unarchived"}, 200
    else:
        return {"error": "could not unarchive"}, 500

@campaña.post("/unirme")
@user_required
def unificar(user: User):
    try:
        data = UnirmeACampaña.parse_raw(request.data)
    except ValidationError as ve:
        print("ValidationError:", ve.errors())  # DEBUG
        parsed_errors = [str(error) for error in ve.errors()]
        # print("parsed:", parsed_errors) 
        return jsonify({"errors": parsed_errors}), 422
    
    jajeo = CampañaDAO()
    emanuel_dio_descontroller = UsuarioCampañaDAO()
    
    prechamba = jajeo.get_by_id(data.id_campaña)
    
    if(prechamba == None):
        return {"error": "No lailo"}, 404
    
    if(prechamba.contraseña != data.contrasenyan):
        return {"error": "Nanailo la contrasenyan uwu"}, 403
    
    premanueh = emanuel_dio_descontroller.get_by_id(user_id=user.idUsuario, campaña_id=data.id_campaña)
    if(premanueh != None):
        return {"success": "bobo, ya etsas dentro"}, 200
    
    manueh = emanuel_dio_descontroller.create(user_id=user.idUsuario, campaña_id=data.id_campaña, ficha_id=None, master=False)
    
    if(manueh == None):
        return {"error": "Me rindo ya ni se"}, 500
    else:
        return {"success": "gucci perron"}, 200

@campaña.post("")
@user_required
def crear(user: User):
    try:
        data = CampañaRequestModel.parse_raw(request.data)
    except ValidationError as ve:
        print("ValidationError:", ve.errors())  # DEBUG
        parsed_errors = [str(error) for error in ve.errors()]
        # print("parsed:", parsed_errors) 
        return jsonify({"errors": parsed_errors}), 422
    
    manual_controller: Campaña_controller = Campaña_controller()
    
    result = manual_controller.crear(data)
    
    if(result == None):
        return {"error": "Failed to create"}, 422
    else:
        emanuel_dio_descontroller = UsuarioCampañaDAO()
        emanuel_dio_descontroller.create(user_id=user.idUsuario, campaña_id=result["idCampaña"], ficha_id=None, master=True)
        return result, 200

@campaña.delete("/<id_usuario>/<id_campanyan>")
@user_required
def rmovear_usuario(user: User, id_usuario: str, id_campanyan: str):
    try:
        id_usuario = int(id_usuario)
    except Exception as e:
        return {"error": f"{id_usuario} user id is not a number"}, 422
    try:
        id_campanyan = int(id_campanyan)
    except Exception as e:
        return {"error": f"{id_campanyan} campaña id is not a number"}, 422
    
    if(id_usuario == user.idUsuario):
        return {"error": "bobo, a ti no te borras"}, 422
    
    emanuel_dio_descontroller = UsuarioCampañaDAO()
    
    admin = emanuel_dio_descontroller.get_by_id(user.idUsuario, id_campanyan)
    if(admin == None):
        return {"error": "No master"}, 403
    elif(admin.master == False):
        return {"error": "No master"}, 403
    
    plebe = emanuel_dio_descontroller.get_by_id(id_usuario, id_campanyan)
    if(plebe == None):
        return {"success": "El usuario ni siquiera etsaba weon"}, 200
    
    deleteado_de_la_bida = emanuel_dio_descontroller.remove_user_from(user_id=id_usuario, campaña_id=id_campanyan)
    
    if(deleteado_de_la_bida == None):
        return {"error": "no se puedo eliminar"}, 500
    else:
        return {"success": f"{id_usuario} ya no esta en {id_campanyan}"}, 200

# @campaña.delete("/<id_campanyan>")
# @user_required
# def unarchive(id_campanyan: str, user: User):
#     id: str
#     try:
#         id = int(id_campanyan)
#     except Exception as e:
#         return {"error": "minusvalid id value"}, 422
    
#     manual_controller: Campaña_controller = Campaña_controller()
    
#     archivada = manual_controller.desarchivar(id)
    
#     if(archivada):
#         return {"success": "unarchived"}, 200
#     else:
#         return {"error": "could not unarchive"}, 500
    
    