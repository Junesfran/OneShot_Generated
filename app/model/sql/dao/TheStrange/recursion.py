from app import db

from app.model.sql.model.TheStrange.recursion import TheStrange_recursion
from app.model.sql.model.TheStrange.apartados import TheStrange_Apartado
from app.model.sql.model.TheStrange.listaTipos import TheStrange_ListaTipos

class TheStrange_recursionDAO:
    def __init__(self):
        self.session = db.session
        
    def get_recursion_list(self):
        return self.session.query(TheStrange_recursion).all()
    
    def get_recursion_by_name(self, recursion_name: str):
        return self.session.query(TheStrange_recursion).filter_by(nombre=recursion_name).first()
    
    def get_tipos_apartado(self) -> list[TheStrange_ListaTipos]:
        return self.session.query(TheStrange_ListaTipos).all()
    
    def get_all_apartados(self) -> list[TheStrange_Apartado]:
        return self.session.query(TheStrange_Apartado).all()
    
    def get_apartados_recursion(self, nombre_recursion: str) -> list[TheStrange_Apartado]:
        return self.session.query(TheStrange_Apartado).filter_by(Recursion_nombre=nombre_recursion).all()