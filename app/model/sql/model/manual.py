from app import db

class Manual(db.Model):
    __tablename__ = "Manual"
    
    id = db.Column(db.String(45), primary_key=True)
    nombre = db.Column(db.String(45))
    imagen = db.Column(db.Text)
    
    def __init__(self, id: str, nombre: str, imagen: str = None):
        self.id = id
        self.nombre = nombre
        self.imagen = imagen
        
    def to_json(self) -> dict:
        return {
            "id": self.id,
            "nombre": self.nombre,
            "imagen": self.imagen
        }