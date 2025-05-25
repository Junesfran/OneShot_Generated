from app import db

class TheStrange_rasgo(db.Model):
    __tablename__ = "Rasgo"
    __bind_key__ = "the_strange"
    
    nombre = db.Column(db.String, primary_key = True)
    descripcion = db.Column(db.Text)
    transferible = db.Column(db.Boolean)
    
    def __init__(self, nombre: str, descripcion: str = None, transferible: bool = False):
        self.nombre
        self.descripcion = descripcion
        self.transferible = transferible
        
    def to_json(self):
        return {
            "nombre": self.nombre,
            "descripcion": self.descripcion,
            "transferible": self.transferible
        }