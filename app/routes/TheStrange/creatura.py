from flask import Blueprint

from app.utils.session import user_required

from app.routes.TheStrange import theStrange_id_prefix

from app.model.sql.model.user import User

from app.model.sql.model.TheStrange.creatura import TheStrange_Criatura
from app.controllers.TheStrange.creatura import TheStrange_creatura_creaturador

theStrange_creaturador = Blueprint("theStrange_creaturas", __name__, url_prefix=f"/{theStrange_id_prefix}/creaturador")

@theStrange_creaturador.get("")
@user_required
def creaturame_bb(user: User):
    count: int
    creaturas: list[TheStrange_Criatura]
    count, creaturas = TheStrange_creatura_creaturador().get_all_creaturas()
    
    if(count == 0):
        return {"error": f"This forsaken land is devoid of life, plant matter and or CREATURAS"}, 404
    
    return {"kuantos": count, "datos": creaturas}, 200

@theStrange_creaturador.get("/<creatura>")
@user_required
# 👉👈
def creaturame_bb_pero_se_gentil(creatura: str, user: User):
    LA_CREATURA = TheStrange_creatura_creaturador().detallame_la_creatura_papa(creatura_name=creatura)
    
    if(LA_CREATURA == None):
        return {"error": f"No whimsical li'l guy named {creatura} is patrolling the hood"}, 404
    
    return LA_CREATURA, 200
    