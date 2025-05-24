from flask import Blueprint, request, jsonify
from app.model.request.user import User_creation_body, User_login_body

from pydantic import ValidationError
from app.model.sql.model.user import User

from app.controllers.user import User_controller

user = Blueprint("user", __name__, url_prefix="/user")

@user.post("/")
def create_user():
    try:
        data = User_creation_body.parse_raw(request.data)
    except ValidationError as ve:
        return jsonify({"error": ve.errors()}), 422
    
    user_controller = User_controller()
    
    try:
        new_user: User = user_controller.create_user(username=data.username, password=data.password)
    except ValueError as ve:
        return {"error": str(ve)}, 409
    
    return new_user.to_json(), 200
    

@user.delete("/<id>")
def delete_user_by_id(id: int):
    user_controller = User_controller()
    
    deleted: bool = user_controller.delete_user(id)
    
    if(deleted):
        return {"Success": f"user with id {id} was removed"}, 200
    else:
        return {"error": f"Te specified user could not be found"}, 404
    
@user.post("/login")
def login():
    try:
        data = User_login_body.parse_raw(request.data)
    except ValidationError as ve:
        return jsonify({"error": ve.errors()}), 422
    
    user_controller = User_controller()
    
    session_token = user_controller.login(username=data.username, password=data.password)

    if(session_token):
        return {"success": "credentials A OK", "token": session_token}, 200
    else:
        return {"error": "invalid credentials"}, 401

@user.post("/logout")
def logout():
    pass