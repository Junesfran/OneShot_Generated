from flask import Blueprint

from app.utils.session import user_required

from app.model.sql.model.user import User
from app.model.sql.model.manual import Manual

from app.controllers.manual import Manual_controller

manual = Blueprint("manual", __name__, url_prefix="/manual")

@manual.get("/")
@user_required
def manual_list(user: User):
    manual_controller: Manual_controller = Manual_controller()
    
    count: int
    manuals: list[dict]
    count, manuals = manual_controller.get_manual_list()
    
    if(count == 0):
        return {"error": "sorry there are no manuals available right now"}, 404
    else:
        return {"count": count, "data": manuals}, 200

@manual.get("/<id>")
@user_required
def get_manual_info(id: str, user: User):
    manual_controller: Manual_controller = Manual_controller()
    
    manual: Manual = manual_controller.get_manual(id)
    
    if(manual == None):
        return {"error": f"manual {id} cannot be found"}, 404
    else:
        return manual.to_json(), 200