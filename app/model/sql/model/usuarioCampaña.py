from app import db

class UsuarioCampaña(db.Model):
    __tablename__ = "UsuarioCampaña"

    Usuario_idUsuario = db.Column(db.Integer, db.ForeignKey('Usuario.idUsuario'), primary_key=True)
    Campaña_idCampaña = db.Column(db.Integer, db.ForeignKey('Campaña.idCampaña'), primary_key=True)
    Ficha_TheStrange_id = db.Column(db.Integer, db.ForeignKey('Ficha_TheStrange.id'), nullable=True)
    master = db.Column(db.Boolean, nullable=False, default=False)

    def __init__(
        self,
        usuario_idUsuario: int,
        campaña_idCampaña: int,
        ficha_the_strange_id: int = None,
        master: bool = False
    ):
        self.Usuario_idUsuario = usuario_idUsuario
        self.Campaña_idCampaña = campaña_idCampaña
        self.Ficha_TheStrange_id = ficha_the_strange_id
        self.master = master

    def to_json(self):
        return {
            "usuario_idUsuario": self.Usuario_idUsuario,
            "campaña_idCampaña": self.Campaña_idCampaña,
            "ficha_the_strange_id": self.Ficha_TheStrange_id,
            "master": self.master
        }
