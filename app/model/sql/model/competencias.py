from app import db

class TheStrange_Competencias(db.Model):
    __tablename__ = "Competencias"

    nombre = db.Column(db.String(45), primary_key=True)
    Ficha_TheStrange_id = db.Column(db.Integer, db.ForeignKey('Ficha_TheStrange.id'), primary_key=True)
    especializado = db.Column(db.Boolean)

    def __init__(self, nombre: str, Ficha_TheStrange_id: int, especializado: bool = False):
        self.nombre = nombre
        self.Ficha_TheStrange_id = Ficha_TheStrange_id
        self.especializado = especializado

    def to_json(self):
        return {
            "nombre": self.nombre,
            # "Ficha_TheStrange_id": self.Ficha_TheStrange_id,
            "especializado": self.especializado
        }
