from app import db

class TheStrange_rasgo(db.Model):
    __tablename__ = "Rasgo"
    __bind_key__ = "the_strange"
    
    nombre = db.Column(db.String, primary_key = True)
    descripion = db.Column(db.Text)
    trasferible = db.Column(db.Boolean)
    
    def __init__(self, nombre: str, descripcion: str = None, transferible: bool = False):
        self.nombre
        self.descripion = descripcion
        self.trasferible = transferible
        
    def to_json(self):
        return {
            "nombre": self.nombre,
            "descripcion": self.descripion,
            "transferible": self.trasferible
        }