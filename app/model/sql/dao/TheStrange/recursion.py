from app import db

from app.model.sql.model.TheStrange.recursion import TheStrange_recursion

class TheStrange_recursionDAO:
    def __init__(self):
        self.session = db.session
        
    def get_recursion_list(self):
        return self.session.query(TheStrange_recursion).all()
    
    def get_recursion_by_name(self, recursion_name: str):
        return self.session.query(TheStrange_recursion).filter_by(nombre=recursion_name).first()