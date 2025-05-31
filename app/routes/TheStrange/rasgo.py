from flask import Blueprint
from app.utils.session import user_required
from app.routes.TheStrange import theStrange_id_prefix

from app.model.sql.dao.TheStrange.rasgo import TheStrange_rasgoDAO

import traceback

rasgo = Blueprint("rasgo", __name__, url_prefix=f"/{theStrange_id_prefix}/rasgo")

@rasgo.get("")
@user_required
def get_all(user):
    rasgos = TheStrange_rasgoDAO().get_all()
    total = len(rasgos)
    if(total == 0):
        return {"error": "Currently there are no rasgo"}, 404
    
    based_rasgos = [rasgo.to_json() for rasgo in rasgos]
    return {"kuantos": total, "datos": based_rasgos}, 200

@rasgo.get("/<name>")
@user_required
def get_by_name(user, name: str):
    try:
        rasgo = TheStrange_rasgoDAO().get_by_name(name)
    except Exception as e:
        print(traceback.format_exc())
    if(rasgo == None):
        return {"error": f"rasgo {rasgo} does not exist"}, 404
    
    return rasgo.to_json(), 200