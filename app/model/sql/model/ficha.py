from app import db

class Ficha_TheStrange(db.Model):
    __tablename__ = "Ficha_TheStrange"

    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    nombre = db.Column(db.String(45))
    clase = db.Column(db.String(45))
    experiencia = db.Column(db.Integer)
    esfuerzo = db.Column(db.Integer)
    limiteDispositivos = db.Column(db.Integer)
    recuperacion = db.Column(db.String(10))
    trasfondo = db.Column(db.LargeBinary)
    descriptor = db.Column(db.String(45))
    vinculoDescriptor = db.Column(db.String(45))
    Usuario_idUsuario = db.Column(db.Integer, db.ForeignKey('Usuario.idUsuario'), nullable=False)
    Manual_id = db.Column(db.String(45), db.ForeignKey('Manual.id'), nullable=False)
    tipo = db.Column(db.String(45))
    rasgo = db.Column(db.String(200))
    recursion = db.Column(db.String(45))
    rango = db.Column(db.Integer)
    aumentarC = db.Column(db.Boolean)
    perfeccion = db.Column(db.Boolean)
    esfuerzoExtra = db.Column(db.Boolean)
    competenciaH = db.Column(db.Boolean)
    otros = db.Column(db.Boolean)
    accion = db.Column(db.Boolean)
    hora = db.Column(db.Boolean)
    minutos = db.Column(db.Boolean)
    horas = db.Column(db.Boolean)
    vigorAct = db.Column(db.Integer)
    reservaVigorMax = db.Column(db.Integer)
    ventajaVigorMax = db.Column(db.Integer)
    velocidadAct = db.Column(db.Integer)
    reservaVelocidadMax = db.Column(db.Integer)
    ventajaVelocidadMax = db.Column(db.Integer)
    inteligenciaAct = db.Column(db.Integer)
    reservaInteligenciaMax = db.Column(db.Integer)
    ventajaInteligenciaMax = db.Column(db.Integer)
    maxDispositivos = db.Column(db.Integer)
    armadura = db.Column(db.Integer)
    dinero = db.Column(db.Integer)

    def __init__(
    self,
    nombre: str = None,
    clase: str = None,
    experiencia: int = None,
    esfuerzo: int = None,
    limiteDispositivos: int = None,
    recuperacion: str = None,
    trasfondo: bytes = None,
    descriptor: str = None,
    vinculoDescriptor: str = None,
    Usuario_idUsuario: int = None,
    Manual_id: str = None,
    tipo: str = None,
    rasgo: str = None,
    recursion: str = None,
    rango: int = None,
    aumentarC: bool = None,
    perfeccion: bool = None,
    esfuerzoExtra: bool = None,
    competenciaH: bool = None,
    otros: bool = None,
    accion: bool = None,
    hora: bool = None,
    minutos: bool = None,
    horas: bool = None,
    vigorAct: int = None,
    reservaVigorMax: int = None,
    ventajaVigorMax: int = None,
    ventajaVigorAct: int = None,
    velocidadAct: int = None,
    reservaVelocidadMax: int = None,
    ventajaVelocidadMax: int = None,
    inteligenciaAct: int = None,
    reservaInteligenciaMax: int = None,
    ventajaInteligenciaMax: int = None,
    maxDispositivos: int = None,
    armadura: int = None,
    dinero: int = None
    ):
        self.nombre = nombre
        self.clase = clase
        self.experiencia = experiencia
        self.esfuerzo = esfuerzo
        self.limiteDispositivos = limiteDispositivos
        self.recuperacion = recuperacion
        self.trasfondo = trasfondo
        self.descriptor = descriptor
        self.vinculoDescriptor = vinculoDescriptor
        self.Usuario_idUsuario = Usuario_idUsuario
        self.Manual_id = Manual_id
        self.tipo = tipo
        self.rasgo = rasgo
        self.recursion = recursion
        self.rango = rango
        self.aumentarC = aumentarC
        self.perfeccion = perfeccion
        self.esfuerzoExtra = esfuerzoExtra
        self.competenciaH = competenciaH
        self.otros = otros
        self.accion = accion
        self.hora = hora
        self.minutos = minutos
        self.horas = horas
        self.vigorAct = vigorAct
        self.reservaVigorMax = reservaVigorMax
        self.ventajaVigorMax = ventajaVigorMax
        self.ventajaVigorAct = ventajaVigorAct
        self.velocidadAct = velocidadAct
        self.reservaVelocidadMax = reservaVelocidadMax
        self.ventajaVelocidadMax = ventajaVelocidadMax
        self.inteligenciaAct = inteligenciaAct
        self.reservaInteligenciaMax = reservaInteligenciaMax
        self.ventajaInteligenciaMax = ventajaInteligenciaMax
        self.maxDispositivos = maxDispositivos
        self.armadura = armadura
        self.dinero = dinero


    def to_json(self):
        return {column.name: getattr(self, column.name) for column in self.__table__.columns}
        # return {"webi": "wabo"}
