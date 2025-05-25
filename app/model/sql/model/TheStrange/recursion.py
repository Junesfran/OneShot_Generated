from app import db

class TheStrange_recursion(db.Model):
    __tablename__ = "Recursion"
    __bind_key__ = "the_strange"
    
    nombre = db.Column(db.String(45), primary_key = True)
    resumen = db.Column(db.Text)
    nivel = db.Column(db.Integer)
    conexionTheStrange = db.Column(db.Text)
    conexionTierra = db.Column(db.Text)
    forma = db.Column(db.Text)
    chispa = db.Column(db.String(45))
    divisa = db.Column(db.String(45))
    mapa = db.Column(db.Text)
    
    def __init__(self, nombre: str, resumen: str = None, nivel: int = None, conexionTheStrange: str = None, conexionTierra: str = None, forma: str = None, chispa: str = None, divisa: str = None, mapa: str = None):
        self.nombre = nombre
        self.resumen = resumen
        self.nivel = nivel
        self.conexionTheStrange = conexionTheStrange
        self.conexionTierra = conexionTierra
        self.forma = forma
        self.chispa = chispa
        self.divisa = divisa
        self.mapa = mapa
        
    def to_json(self):
        return {
            "nombre": self.nombre,
            "resumen": self.resumen,
            "nivel": self.nivel,
            "conexion_the_strange": self.conexionTheStrange,
            "conexion_tierra": self.conexionTierra,
            "forma": self.forma,
            "chispa": self.chispa,
            "divisa": self.divisa,
            "mapa": self.mapa
        }