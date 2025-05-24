from flask import request
from traceback import format_exc
from app.model.sql.model.user import User
from app.model.sql.dao.user import UserDAO
from app.utils.security import verify_and_extract_token

def user_required():
    def wrapper(function):
        def second_wrap(*args, **kwargs):
            auth_header: str = request.headers.get("Authorization")
            
            if(not auth_header):
                return {"error": "Authorization header is not present"}, 401
            if(auth_header[:7] != "Bearer "):
                return {"error": "Malformed Bearer token"}, 401
            token: str = auth_header[7:]
            if(len(token) == 0):
                return {"error": "No token was provided"}, 401
            
            try:
                token_payload: dict = verify_and_extract_token(token)
            except Exception as e:
                print(format_exc())
                return {"error": "Invalid token"}, 401
            
            # TODO: hacer esta wea
            user: User = UserDAO().get_by_id(token)
            
            
        return second_wrap
    return wrapper