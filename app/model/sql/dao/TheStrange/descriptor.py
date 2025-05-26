from app import db

from app.model.sql.model.TheStrange.descriptor import TheStrange_descriptor
from app.model.sql.model.TheStrange.descriptorRecursion import TheStrange_descriptorRecursion
from app.model.sql.model.TheStrange.recursion import TheStrange_recursion

class TheStrange_descriptorDAO:
    def __init__(self):
        self.session = db.session
        
    def get_descriptor_list(self) -> list[TheStrange_descriptor]:
        return self.session.query(TheStrange_descriptor).all()
        
    def get_spicy_descriptor_for_recursion(self, recursion_name: str) -> TheStrange_descriptor | None:
        return self.session.query(TheStrange_descriptor).join(TheStrange_descriptorRecursion).join(TheStrange_recursion).filter(TheStrange_recursion.nombre == recursion_name).first()