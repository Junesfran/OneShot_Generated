from flask import Blueprint

from app.utils.session import user_required

from app.routes.TheStrange import theStrange_id_prefix

from app.model.sql.model.user import User
from app.controllers.TheStrange.descriptor import TheStrange_descriptor_controller

theStrange_descriptor = Blueprint("theStrange_descriptor", __name__, url_prefix=f"/{theStrange_id_prefix}/descriptor")

@theStrange_descriptor.get("")
@user_required
def get_all_descriptors(user: User):
    count: int
    descriptors: list[dict]
    
    count, descriptors = TheStrange_descriptor_controller().get_all_descriptors()
    
    if(count == 0):
        return {"error": "no descriptors :("}, 404
    
    return {"count": count, "data": descriptors}, 200