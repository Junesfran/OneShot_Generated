from flask import request
from traceback import format_exc
from app.model.sql.model.user import User
from app.model.sql.dao.user import UserDAO
from app.utils.security import verify_and_extract_token

from app.model.request.token import Token_data

from functools import wraps

def user_required(function):
    @wraps(function)
    def wrapper(*args, **kwargs):
        # raise ValueError("pinga")
        auth_header: str = request.headers.get("Authorization")
        
        if(not auth_header):
            return {"error": "Authorization header is not present"}, 401
        if(auth_header[:7] != "Bearer "):
            return {"error": "Malformed Bearer token"}, 401
        token: str = auth_header[7:]
        if(len(token) == 0):
            return {"error": "No token was provided"}, 401
        
        try:
            raw_token_payload: dict = verify_and_extract_token(token)
            token_payload = Token_data(**raw_token_payload)
        except Exception as e:
            print(format_exc())
            return {"error": "Invalid token"}, 401
        
        user: User = UserDAO().get_by_id(token_payload.user_id)
        
        if(user == None):
            print(format_exc())
            return {"error": "Invalid token"}, 401
        
        if(user.token != token):
            print(format_exc())
            return {"error": "Invalid token"}, 401
        
        return function(*args, **kwargs, user = user)
    return wrapper