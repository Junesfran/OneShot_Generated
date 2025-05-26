from app import db

class TheStrange_descriptor(db.Model):
    __tablename__ = "Descriptor"
    __bind_key__ = "the_strange"
    
    nombre = db.Column(db.String(45), primary_key=True)
    descripcion = db.Column(db.Text)
    vinculo = db.Column(db.Text)

    def __init__(self, nombre: str, descripcion: str = None, vinculo: str = None):
        self.nombre = nombre
        self.descripcion = descripcion
        self.vinculo = vinculo

    def to_json(self):
        return {
            "nombre": self.nombre,
            "descripcion": self.descripcion,
            "vinculo": self.vinculo
        }