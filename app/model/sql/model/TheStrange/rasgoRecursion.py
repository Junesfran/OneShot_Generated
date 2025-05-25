from app import db

class TheStrange_rasgoRecursion(db.Model):
    __tablename__ = "RasgoRecursion"
    __bind_key__ = "the_strange"
    
    Recursion_nombre = db.Column(db.String(45), db.ForeignKey("Recursion.nombre"), primarr_key = True)
    Rasgo_nombre = db.Column(db.String(45), db.ForeignKey("Rasgo.nombre"), primarr_key = True)
    
    def __init__(self, recursion_nombre: str, rasgo_nombre: str):
        self.Recursion_nombre = recursion_nombre
        self.Rasgo_nombre = rasgo_nombre
        
    def to_json(self):
        return {
            "recursion_nombre": self.Recursion_nombre,
            "rasgo_nombre": self.Rasgo_nombre
        }