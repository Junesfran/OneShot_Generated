from flask import Blueprint, request, jsonify

from app.model.request.campaña import CampañaRequestModel

from pydantic import ValidationError

from app.utils.session import user_required

from app.model.sql.model.user import User
from app.model.sql.model.campaña import Campaña

from app.controllers.campaña import Campaña_controller

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
        return result, 200

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
    
    