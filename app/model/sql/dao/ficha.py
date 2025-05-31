from app import db

from app.model.sql.model.ficha import Ficha_TheStrange
from app.model.request.ficha import FichaCreateRequest

class FichaDAO:
    def __init__(self):
        self.session = db.session
    
    def get_user_fichas(self, user_id):
        return self.session.query(Ficha_TheStrange).filter_by(Usuario_idUsuario=user_id).all()
    
    def get_by_id(self, user_id, ficha_id):
        return self.session.query(Ficha_TheStrange).filter_by(Usuario_idUsuario=user_id).filter_by(id=ficha_id).first()
    
    def create(self, id_usuario: str,  ficha: FichaCreateRequest):
        novo_du_fich: Ficha_TheStrange = Ficha_TheStrange(
            nombre=ficha.nombre,
            clase=ficha.clase,
            descriptor=ficha.descriptor,
            esfuerzo=ficha.esfuerzo,
            experiencia=ficha.experiencia,
            limiteDispositivos=ficha.limiteDispositivos,
            recuperacion=ficha.recuperacion,
            trasfondo=ficha.trasfondo,
            vinculoDescriptor=ficha.vinculoDescriptor,
            Manual_id="the_stange",
            usuario_idUsuario=id_usuario
        )
        
        self.session.add(novo_du_fich)
        self.session.commit()
        return novo_du_fich