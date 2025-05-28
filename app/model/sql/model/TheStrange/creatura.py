from app import db

class TheStrange_Criatura(db.Model):
    __tablename__ = "Criatura"
    __bind_key__ = "the_strange"

    nombre = db.Column(db.String(45), primary_key=True)
    nivel = db.Column(db.Integer)
    descripcion = db.Column(db.Text)
    motivacion = db.Column(db.Text)
    entorno = db.Column(db.Text)
    salud = db.Column(db.Integer)
    daño = db.Column(db.Integer)
    movimiento = db.Column(db.Text)
    combate = db.Column(db.Text)
    iteraccion = db.Column(db.Text)
    uso = db.Column(db.Text)
    botin = db.Column(db.Text)
    recursion_nombre = db.Column(db.String(45), db.ForeignKey('Recursion.nombre'))

    def __init__(
        self,
        nombre: str,
        nivel: int = None,
        descripcion: str = None,
        motivacion: str = None,
        entorno: str = None,
        salud: int = None,
        daño: int = None,
        movimiento: str = None,
        combate: str = None,
        iteraccion: str = None,
        uso: str = None,
        botin: str = None,
        recursion_nombre: str = None,
    ):
        self.nombre = nombre
        self.nivel = nivel
        self.descripcion = descripcion
        self.motivacion = motivacion
        self.entorno = entorno
        self.salud = salud
        self.daño = daño
        self.movimiento = movimiento
        self.combate = combate
        self.iteraccion = iteraccion
        self.uso = uso
        self.botin = botin
        self.recursion_nombre = recursion_nombre

    def to_json(self):
        return {
            "nombre": self.nombre,
            "nivel": self.nivel,
            "descripcion": self.descripcion,
            "motivacion": self.motivacion,
            "entorno": self.entorno,
            "salud": self.salud,
            "daño": self.daño,
            "movimiento": self.movimiento,
            "combate": self.combate,
            "iteraccion": self.iteraccion,
            "uso": self.uso,
            "botin": self.botin,
            "recursion_nombre": self.recursion_nombre
        }
