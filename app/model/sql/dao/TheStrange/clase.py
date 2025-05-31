from app import db

from app.model.sql.model.TheStrange.clase import TheStrange_Clase

class TheStrange_claseDAO:
    def __init__(self):
        self.session = db.session
        
    def get_all(self):
        return self.session.query(TheStrange_Clase).all()
        
    def get_by_name(self, name: str):
        return self.session.query(TheStrange_Clase).filter_by(nombre=name).first()