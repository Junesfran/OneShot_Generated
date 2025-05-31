from app import db

class TheStrange_Clase(db.Model):
    __tablename__ = "Clase"
    __bind_key__ = "the_strange"

    nombre = db.Column(db.String(45), primary_key=True)
    traslacion = db.Column(db.String(45))
    minimoDispositivos = db.Column(db.Integer)
    descripcion = db.Column(db.Text)
    vinculos = db.Column(db.Text)

    def __init__(
        self,
        nombre: str,
        traslacion: str = None,
        minimoDispositivos: int = None,
        descripcion: str = None,
        vinculos: str = None
    ):
        self.nombre = nombre
        self.traslacion = traslacion
        self.minimoDispositivos = minimoDispositivos
        self.descripcion = descripcion
        self.vinculos = vinculos

    def to_json(self):
        return {
            "nombre": self.nombre,
            "traslacion": self.traslacion,
            "minimoDispositivos": self.minimoDispositivos,
            "descripcion": self.descripcion,
            "vinculos": self.vinculos
        }
