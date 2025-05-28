from app.model.sql.model.campaña import Campaña
from app.model.sql.dao.campaña import CampañaDAO

from app.model.request.campaña import CampañaRequestModel

class Campaña_controller:
    def __init__(self):
        self.campaña_dao = CampañaDAO()
    
    def get_camaña(self, manual_id: str) -> dict|None:
        campaña: Campaña = self.campaña_dao.get_by_id(id=manual_id)
        
        return campaña.to_json()
    
    def get_all_camapaña(self) -> tuple[int, list[dict]]:
        campañas: list[Campaña] = self.campaña_dao.get_all()
        
        adapted_campaña = [microplastik.to_json() for microplastik in campañas]
        
        return len(campañas), adapted_campaña
    
    def get_all_camapaña_for_user(self, user_id: int) -> tuple[int, list[dict]]:
        campañas: list[Campaña] = self.campaña_dao.get_all_for_user(user_id=id)
        
        adapted_manueles = [microplastik.to_json() for microplastik in campañas]
        
        return len(campañas), adapted_manueles
    
    def archivar(self, id: int) -> bool:
        try:
            return self.campaña_dao.archivar(id=id)
        except Exception as e:
            return False
    
    def desarchivar(self, id: int) -> bool:
        try:
            return self.campaña_dao.desarchivar(id=id)
        except Exception as e:
            return False
    
    def boorar(self, id: int) -> bool:
        try:
            return self.campaña_dao.delete(id=id)
        except ValueError as ve:
            raise ValueError(str(ve))
        except Exception as e:
            return False
    
    def crear(self, campaña: CampañaRequestModel) -> dict|None:
        try:
            result = self.campaña_dao.crear(data=campaña)
            if(result != None):
                return result.to_json()
            return None
        except Exception as e:
            return None