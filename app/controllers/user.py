from app.model.sql.model.user import User

from app.model.sql.dao.user import UserDAO

from app.utils.security import hash_string_as_string, gen_jwt

class User_controller:
    def __init__(self):
        pass
    
    def create_user(self, username: str, password: str) -> User:
        user_dao = UserDAO()
        
        if(user_dao.get_by_name(username=username)):
            raise ValueError(f"{username} is already in use")
        
        new_user: User = user_dao.create(username=username, password=password)
        
        return new_user
    
    def delete_user(self, id: int) -> bool:
        user_dao = UserDAO()
        
        new_user: User = user_dao.delete_by_id(id=id)
        
        return(new_user != None)
    
    def login(self, username: str, password: str) -> str|None:
        user_dao = UserDAO()
        
        user: User = user_dao.get_by_name(username=username)
        
        if(user):
            password_hash: str = hash_string_as_string(password)
            if(password_hash == user.hash_contraseña):
                session_token = gen_jwt(user_id=user.idUsuario, username=user.nombre)
                
                user_dao.set_token_for(user.idUsuario, session_token)
                
                return session_token
        
        return None
    
    def logout(self, user_id: int) -> bool:
        user_dao = UserDAO()
        
        return user_dao.set_token_for(user_id, None)