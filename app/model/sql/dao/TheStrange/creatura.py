from app import db

from app.model.sql.model.TheStrange.creatura import TheStrange_Criatura
from app.model.sql.model.TheStrange.recursion import TheStrange_recursion

class TheStrange_creaturador:
    def __init__(self):
        self.session = db.session
        
    def get_creatura_list(self) -> list[TheStrange_Criatura]:
        return self.session.query(TheStrange_Criatura).all()
        
    def get_creatura(self, creatura_name: str) -> TheStrange_Criatura | None:
        return self.session.query(TheStrange_Criatura).filter_by(nombre=creatura_name).first()
        
    def get_creatura_by_recursion(self, recursion_name: str) -> list[TheStrange_Criatura]:
        return self.session.query(TheStrange_Criatura).join(TheStrange_recursion).filter(TheStrange_recursion.nombre == recursion_name).all()