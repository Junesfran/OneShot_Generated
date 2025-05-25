from app.model.sql.model.manual import Manual
from app.model.sql.dao.manual import ManualDAO

class Manual_controller:
    def __init__(self):
        self.manual_dao = ManualDAO()
    
    def get_manual(self, manual_id: str) -> Manual|None:
        manuel: Manual = self.manual_dao.get_by_id(id=manual_id)
        
        return manuel
    
    def get_manual_list(self) -> tuple[int, list[dict]]:
        manueles: list[Manual] = self.manual_dao.get_all()
        
        adapted_manueles = [macromanuel.to_json() for macromanuel in manueles]
        
        return len(manueles), adapted_manueles