from app import db

class Ficha_TheStrange(db.Model):
    __tablename__ = "Ficha_TheStrange"

    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    nombre = db.Column(db.String(45))
    clase = db.Column(db.String(45))
    experiencia = db.Column(db.Integer)
    esfuerzo = db.Column(db.Integer)
    limiteDispositivos = db.Column(db.Integer)
    recuperacion = db.Column(db.String(10))
    trasfondo = db.Column(db.LargeBinary)
    descriptor = db.Column(db.String(45))
    vinculoDescriptor = db.Column(db.String(45))
    Usuario_idUsuario = db.Column(db.Integer, db.ForeignKey('Usuario.idUsuario'), nullable=False)
    Manual_id = db.Column(db.String(45), db.ForeignKey('Manual.id'), nullable=False)

    def __init__(
        self,
        nombre: str = None,
        clase: str = None,
        experiencia: int = None,
        esfuerzo: int = None,
        limiteDispositivos: int = None,
        recuperacion: str = None,
        trasfondo: bytes = None,
        descriptor: str = None,
        vinculoDescriptor: str = None,
        usuario_idUsuario: int = None,
        Manual_id: str = None
    ):
        self.nombre = nombre
        self.clase = clase
        self.experiencia = experiencia
        self.esfuerzo = esfuerzo
        self.limiteDispositivos = limiteDispositivos
        self.recuperacion = recuperacion
        self.trasfondo = trasfondo
        self.descriptor = descriptor
        self.vinculoDescriptor = vinculoDescriptor
        self.Usuario_idUsuario = usuario_idUsuario
        self.Manual_id = Manual_id

    def to_json(self):
        return {
            "id": self.id,
            "nombre": self.nombre,
            "clase": self.clase,
            "experiencia": self.experiencia,
            "esfuerzo": self.esfuerzo,
            "limiteDispositivos": self.limiteDispositivos,
            "recuperacion": self.recuperacion,
            "trasfondo": self.trasfondo.decode("utf-8"),  # Podés codificarlo si querés JSON puro
            "descriptor": self.descriptor,
            "vinculoDescriptor": self.vinculoDescriptor,
            "Usuario_idUsuario": self.Usuario_idUsuario,
            "Manual_id": self.Manual_id
        }
