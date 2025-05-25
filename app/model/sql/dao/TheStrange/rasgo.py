from app import db

from app.model.sql.model.TheStrange.rasgo import TheStrange_rasgo
from app.model.sql.model.TheStrange.rasgoRecursion import TheStrange_rasgoRecursion
from app.model.sql.model.TheStrange.recursion import TheStrange_recursion

class TheStrange_rasgoDAO:
    def __init__(self):
        self.session = db.session
        
    def get_rasgo_for_recursion(self, recursion_name: str) -> list[TheStrange_rasgo]:
        return self.session.query(TheStrange_rasgo).join(TheStrange_rasgoRecursion).join(TheStrange_recursion).filter(TheStrange_recursion.nombre==recursion_name).all()