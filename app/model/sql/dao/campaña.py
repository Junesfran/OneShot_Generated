from app import db

import traceback

from app.model.request.campaña import CampañaRequestModel
from app.model.sql.model.campaña import Campaña
from app.model.sql.model.usuarioCampaña import UsuarioCampaña

class CampañaDAO:
    def __init__(self):
        self.session = db.session
    
    def get_all(self) -> list[Campaña]:
        return self.session.query(Campaña).all()
    
    def get_all_for_user(self, user_id: int) -> list[Campaña]:
        return self.session.query(Campaña).join(UsuarioCampaña).filter(UsuarioCampaña.Usuario_idUsuario==user_id).all()
    
    def get_by_id(self, id: int) -> Campaña|None:
        return self.session.query(Campaña).filter_by(id=id).first()
    
    def delete(self, id: int) -> bool:
        campaña = self.get_by_id(id)
        
        if(campaña == None):
            raise ValueError(f"campaña {id} does not exist")
        
        try:
            self.session.remove(campaña)
            self.session.commit()
            return True
        except Exception as e:
            return False
        
    def archivar(self, id: str) -> bool:
        campaña = self.get_by_id(id)
        
        if(campaña == None):
            raise ValueError(f"campaña {id} does not exist")
        
        try:
            campaña.archivada = True
            self.session.commit()
            return True
        except Exception as e:
            return False
        
    def desarchivar(self, id: str) -> bool:
        campaña = self.get_by_id(id)
        
        if(campaña == None):
            raise ValueError(f"campaña {id} does not exist")
        
        try:
            campaña.archivada = False
            self.session.commit()
            return True
        except Exception as e:
            return False
        
    def crear(self, data: CampañaRequestModel) -> Campaña|None:
        try:
            campaña_sql = Campaña(archivada=False,
                                descripcion=data.descripcion,
                                imagen=data.idImagen,
                                manual_id=data.manual,
                                nombre=data.nombre,
                                contraseña=data.contrasenyn
                                )
            self.session.add(campaña_sql)
            self.session.commit()
            return campaña_sql
        except Exception as e:
            print(traceback.format_exc())
            return None