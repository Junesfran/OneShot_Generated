from app.model.sql.dao.TheStrange.rasgo import TheStrange_rasgoDAO
from app.model.sql.dao.TheStrange.recursion import TheStrange_recursionDAO
from app.model.sql.dao.TheStrange.descriptor import TheStrange_descriptorDAO
class TheStrange_rasgo_controller:
    def __init__(self):
        self.rasgo_dao = TheStrange_rasgoDAO()
        self.recursion_dao = TheStrange_recursionDAO()
        
    def get_rasgos_for_recursion(self, recursion_name: str) -> tuple[int, list[dict]]:
        recursion = self.recursion_dao.get_recursion_by_name(recursion_name=recursion_name)
        
        if(recursion == None):
            raise ValueError("The provided recursion does not exist")
        
        rasgos = self.rasgo_dao.get_rasgo_for_recursion(recursion_name=recursion_name)
        
        rasgos_basados = [rasgo.to_json() for rasgo in rasgos]
        count = len(rasgos_basados)
        
        return count, rasgos_basados