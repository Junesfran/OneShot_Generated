from app import db

class Campaña(db.Model):
    __tablename__ = "Campaña"

    idCampaña = db.Column(db.Integer, primary_key=True, autoincrement=True)
    nombre = db.Column(db.String(45))
    descripcion = db.Column(db.Text)
    imagen = db.Column(db.Integer)
    manual_id = db.Column(db.String(45), db.ForeignKey('Manual.id'))
    contraseña = db.Column(db.String(45), nullable=False)
    archivada = db.Column(db.Boolean, nullable=False, default=False)

    def __init__(
        self,
        nombre: str = None,
        descripcion: str = None,
        imagen: int = None,
        manual_id: str = None,
        contraseña: str = "webi wabo",
        archivada: bool = False
    ):
        self.nombre = nombre
        self.descripcion = descripcion
        self.imagen = imagen
        self.manual_id = manual_id
        self.contraseña = contraseña
        self.archivada = archivada

    def to_json(self):
        return {
            "idCampaña": self.idCampaña,
            "nombre": self.nombre,
            "descripcion": self.descripcion,
            "imagen": self.imagen,
            "manual_id": self.manual_id,
            "archivada": self.archivada,
            "contraseña": "ya te gustaria eh cachondo vete a tocar hierba o algo"
        }
