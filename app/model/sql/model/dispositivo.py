from app import db

class TheStrange_Dispositivo(db.Model):
    __tablename__ = "Dispositivo"

    nombre = db.Column(db.String(45), primary_key=True)
    Ficha_TheStrange_id = db.Column(db.Integer, db.ForeignKey('Ficha_TheStrange.id'), primary_key=True)

    def __init__(self, nombre: str, Ficha_TheStrange_id: int):
        self.nombre = nombre
        self.Ficha_TheStrange_id = Ficha_TheStrange_id

    def to_json(self):
        return {
            "nombre": self.nombre,
            # "Ficha_TheStrange_id": self.Ficha_TheStrange_id
        }
