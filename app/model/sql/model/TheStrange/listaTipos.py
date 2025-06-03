from app import db

class TheStrange_ListaTipos(db.Model):
    __tablename__ = "listaTipos"
    __bind_key__ = "the_strange"

    idTipos = db.Column(db.Integer, primary_key=True)
    tipo = db.Column(db.String(45))

    def __init__(
        self,
        idApartado: int,
        tipo: str = None,
    ):
        self.idTipos = idApartado
        self.tipo = tipo

    def to_json(self):
        return {
            "idApartado": self.idTipos,
            "tipo": self.tipo
        }