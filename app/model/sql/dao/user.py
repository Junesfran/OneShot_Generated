from app import db
from app.model.sql.model.user import User

class UserDAO:
    def __init__(self):
        self.session = db.session
        
    def create(self, username: str, password: str):
        user = User(nombre=username, contraseña=password)
        self.session.add(user)
        self.session.commit()
        return user
    
    def get_by_id(self, id: int) -> User|None:
        return self.session.query(User).filter_by(idUsuario=id).first()
    
    def get_by_name(self, username: str) -> User|None:
        return self.session.query(User).filter_by(nombre=username).first()
    
    def delete_by_id(self, id: int) -> User|None:
        user = self.get_by_id(id)
        if(user):
            self.session.delete(user)
            self.session.commit()
        return user
    
    def set_token_for(self, id: int, token: str|None) -> bool:
        user = self.get_by_id(id)
        if(user):
            user.token = token
            self.session.commit()
            return True
        return False
        