from flask import Blueprint, request, jsonify

from app.utils.session import user_required

from app.model.sql.dao.ficha import FichaDAO

from app.model.request.ficha import FichaCreateRequest

from app.routes.TheStrange import theStrange_id_prefix

from pydantic import ValidationError

import traceback

ficha = Blueprint("ficha", __name__, url_prefix=f"/{theStrange_id_prefix}/ficha")

@ficha.get("")
@user_required
def get_all(user):
    fichas = FichaDAO().get_user_fichas(user.idUsuario)
    total = len(fichas)
    if(total == 0):
        return {"error": "Currently there are no fichas 4 u"}, 404
    
    based_fichas = [{"id": ficha.id, "nombre": ficha.nombre, "clase": ficha.clase} for ficha in fichas]
    return {"kuantos": total, "datos": based_fichas}, 200

@ficha.delete("/<id>")
@user_required
def fetus_deletus(user, id: str):
    fichador = FichaDAO()
    ficha = fichador.get_by_id(user.idUsuario, id)
    if(ficha == None):
        return {"error", "nonnonon"}, 404
    
    try:
        FichaDAO().yeet(id)
        return {"success": "borrindada"}, 200
    except Exception as e:
        return {"error": f"no se: {str(e)}"}, 500

@ficha.get("/<id>")
@user_required
def get_by_name(user, id: str):
    try:
        ficha, capacidades, competencias, dispositivos, equipo = FichaDAO().get_by_id(user.idUsuario, id)
    except Exception as e:
        print(traceback.format_exc())
    if(ficha == None):
        return {"error": f"no {id} ficha"}, 404
    
    datardo = ficha.to_json()
    datardo["capacidades_especiales"] = [capacidad.to_json() for capacidad in capacidades]
    datardo["competencias"] = [competencia.to_json() for competencia in competencias]
    datardo["dispositivos"] = [dispositivo.to_json() for dispositivo in dispositivos]
    datardo["equipo"] = [equipo.to_json() for equipo in equipo]
    return datardo, 200

@ficha.post("")
@user_required
def crear(user):
    try:
        data = FichaCreateRequest.parse_raw(request.data)
    except ValidationError as ve:
        print("ValidationError:", ve.errors())  # DEBUG
        parsed_errors = [str(error) for error in ve.errors()]
        # print("parsed:", parsed_errors) 
        return jsonify({"errors": parsed_errors}), 422
    
    
    try:
        result = FichaDAO().create(user.idUsuario, data)
    except Exception as e:
        print(traceback.format_exc())
    
    if(result == None):
        return {"error": "Failed to create"}, 422
    else:
        try:
            ficha, capacidades, competencias, dispositivos, equipo = FichaDAO().get_by_id(user.idUsuario, result.id)
        except Exception as e:
            print(traceback.format_exc())
        if(ficha == None):
            return {"error": f"no {id} ficha"}, 404
        
        datardo = ficha.to_json()
        datardo["capacidades_especiales"] = [capacidad.to_json() for capacidad in capacidades]
        datardo["competencias"] = [competencia.to_json() for competencia in competencias]
        datardo["dispositivos"] = [dispositivo.to_json() for dispositivo in dispositivos]
        datardo["equipo"] = [equipo.to_json() for equipo in equipo]
        return datardo, 200