from app import db
from uuid import uuid4
from app.utils.security import hash_string_as_string

class User(db.Model):
    __tablename__ = "Usuario"
    
    idUsuario = db.Column(db.Integer, primary_key=True, autoincrement=True)
    nombre = db.Column(db.String(45))
    contraseña = db.Column(db.String(45))
    hash_contraseña = db.Column(db.String(128))
    token = db.Column(db.String(100))
    
    def __init__(self, nombre: str, contraseña: str):
        self.nombre = nombre
        self.contraseña = contraseña
        self.hash_contraseña = hash_string_as_string(contraseña)
        self.token = None
        
    def to_json(self) -> dict:
        return {
            "id_usuario": self.idUsuario,
            "nombre": self.nombre,
            "token": self.token
        }