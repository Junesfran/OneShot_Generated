from app import db

from app.model.sql.model.manual import Manual

class ManualDAO:
    def __init__(self):
        self.session = db.session
    
    def get_all(self) -> list[Manual]:
        return self.session.query(Manual).all()
    
    def get_by_id(self, id: int) -> Manual|None:
        return self.session.query(Manual).filter_by(id=id).first()