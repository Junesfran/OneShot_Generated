from app import db

class TheStrange_Apartado(db.Model):
    __tablename__ = "Apartado"
    __bind_key__ = "the_strange"

    idApartado = db.Column(db.Integer, primary_key=True)
    titulo = db.Column(db.String(45))
    texto = db.Column(db.Text)
    listaTipos_idTipos = db.Column(db.Integer, db.ForeignKey('listaTipos.idTipos'), nullable=False)
    Recursion_nombre = db.Column(db.String(45), db.ForeignKey('Recursion.nombre'), nullable=False)

    def __init__(
        self,
        idApartado: int,
        titulo: str = None,
        texto: str = None,
        listaTipos_idTipos: int = None,
        Recursion_nombre: str = None
    ):
        self.idApartado = idApartado
        self.titulo = titulo
        self.texto = texto
        self.listaTipos_idTipos = listaTipos_idTipos
        self.Recursion_nombre = Recursion_nombre

    def to_json(self):
        return {
            "idApartado": self.idApartado,
            "titulo": self.titulo,
            "texto": self.texto,
            "listaTipos_idTipos": self.listaTipos_idTipos,
            "Recursion_nombre": self.Recursion_nombre
        }