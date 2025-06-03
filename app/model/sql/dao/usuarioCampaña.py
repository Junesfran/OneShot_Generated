from app import db
from app.model.sql.model.usuarioCampaña import UsuarioCampaña

class UsuarioCampañaDAO:
    def __init__(self):
        self.session = db.session
        
    def create(self, user_id, campaña_id, ficha_id = None, master = False):
        try:
            user = UsuarioCampaña(usuario_idUsuario=user_id, campaña_idCampaña=campaña_id, ficha_the_strange_id=ficha_id, master=master)
            self.session.add(user)
            self.session.commit()
        except Exception as e:
            return None
        return user
    
    def get_by_id(self, user_id: int, campaña_id: int):
        return self.session.query(UsuarioCampaña).filter_by(Campaña_idCampaña=campaña_id, Usuario_idUsuario=user_id).first()
    
    def get_all_by_campaña(self, campaña_id: int):
        return self.session.query(UsuarioCampaña).filter_by(Campaña_idCampaña=campaña_id).all()
    
    def get_campaña_users(self, campaña_id: int):
        datardos = self.session.query(UsuarioCampaña).filter_by(Campaña_idCampaña=campaña_id).all()
        based_datardos = [microplastik.Usuario_idUsuario for microplastik in datardos]
        return based_datardos
    
    def get_user_campañas(self, user_id: int):
        datardos = self.session.query(UsuarioCampaña).filter_by(Usuario_idUsuario=user_id).all()
        based_datardos = [microplastik.Usuario_idUsuario for microplastik in datardos]
        return based_datardos
    
    def remove_user_from(self, user_id: int, campaña_id: int):
        user = self.get_by_id(user_id=user_id, campaña_id=campaña_id)
        if(user):
            self.session.delete(user)
            self.session.commit()
        return user
    
    def add_ficharda(self, user_id: int, campaña_id: int, ficharda_id: int):
        register = self.get_by_id(user_id=user_id, campaña_id=campaña_id)
        
        if(register == None):
            raise ValueError("not found")
        
        try:
            register.Ficha_TheStrange_id = ficharda_id
            self.session.commit()
            return register
        except Exception as e:
            self.session.rollback()
            raise ValueError(str(e))