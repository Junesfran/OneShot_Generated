from flask import Blueprint

from app.utils.session import user_required

from app.routes.TheStrange import theStrange_id_prefix

from app.model.sql.model.user import User
from app.model.sql.model.TheStrange.recursion import TheStrange_recursion
from app.controllers.TheStrange.recursion import TheStrange_recursion_controller
from app.controllers.TheStrange.rasgo import TheStrange_rasgo_controller
from app.controllers.TheStrange.descriptor import TheStrange_descriptor_controller

theStrange_recursion = Blueprint("theStrange_recursion", __name__, url_prefix=f"/{theStrange_id_prefix}/recursion")

@theStrange_recursion.get("")
@user_required
def get_recursion_list(user: User):
    recursion_controller : TheStrange_recursion_controller = TheStrange_recursion_controller()
    
    count: int
    recursiones: list[dict]
    count, recursiones = recursion_controller.get_recursion_list()
    
    if(count == 0):
        return {"error": "no recursion is currently available"}, 404
    else:
        return {"count": count, "data": recursiones}, 200

@theStrange_recursion.get("/<name>")    
@user_required
def get_recursion_by_name(name: str, user: User):
    recursion_controller : TheStrange_recursion_controller = TheStrange_recursion_controller()
    
    recursion = recursion_controller.get_recursion(name)
    
    if(recursion == None):
        return {"error": f"recursion {name} does not exist"}, 404
    else:
        return recursion.to_json(), 200
    
@theStrange_recursion.get("/<recursion>/rasgos")
@user_required
def get_rasgos_for_recursion(recursion: str, user: User):
    rasgos_controller: TheStrange_rasgo_controller = TheStrange_rasgo_controller()
    
    try:
        rasgos: list[dict]
        count: int
        count, rasgos = rasgos_controller.get_rasgos_for_recursion(recursion)
        
        if(count == 0):
            return {"error": f"no rasgos for {recursion} recursion"}, 404
        
        return {"count": count, "data": rasgos}, 200
    except ValueError as ve:
        return {"error": str(ve)}, 422
    
@theStrange_recursion.get("/<recursion>/spicyDescriptor")
@user_required
def get_spicy_descriptor(recursion: str, user: User):
    try:
        spice_master = TheStrange_descriptor_controller().get_spicy_descriptor_for(recursion)
    except ValueError as ve:
        return {"error": str(ve)}, 404
        
    return spice_master, 200