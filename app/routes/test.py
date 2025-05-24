from flask import Blueprint

from app.model.sql.model.user import User

from app.utils.session import user_required

test = Blueprint("test", __name__, url_prefix="/test")

@test.get("/")
def holi():
    return "holi", 200

@user_required()
@test.get("/protected")
def holi_pero_protegido(user: User):
    return f"holi pero con condon, de parte de {user.nombre}", 200