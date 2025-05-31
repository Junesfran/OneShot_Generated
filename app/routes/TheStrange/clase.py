from flask import Blueprint
from app.utils.session import user_required
from app.routes.TheStrange import theStrange_id_prefix

from app.model.sql.dao.TheStrange.clase import TheStrange_claseDAO

import traceback

clase = Blueprint("clase", __name__, url_prefix=f"/{theStrange_id_prefix}/clase")

@clase.get("")
@user_required
def get_all(user):
    clases = TheStrange_claseDAO().get_all()
    total = len(clases)
    if(total == 0):
        return {"error": "Currently there are no clase"}, 404
    
    based_clases = [clase.to_json() for clase in clases]
    return {"kuantos": total, "datos": based_clases}, 200

@clase.get("/<name>")
@user_required
def get_by_name(user, name: str):
    try:
        clase = TheStrange_claseDAO().get_by_name(name)
    except Exception as e:
        print(traceback.format_exc())
    if(clase == None):
        return {"error": f"clase {clase} does not exist"}, 404
    
    return clase.to_json(), 200