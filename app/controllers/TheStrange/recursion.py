from app.model.sql.dao.TheStrange.recursion import TheStrange_recursionDAO
from app.model.sql.model.TheStrange.recursion import TheStrange_recursion

class TheStrange_recursion_controller:
    def __init__(self):
        self.recursion_dao = TheStrange_recursionDAO()
    
    def get_recursion_list(self) -> tuple[int, list[dict]]:
        recursiones: list[TheStrange_recursion] = self.recursion_dao.get_recursion_list()
        
        count: int = len(recursiones)
        
        recursiones_crackeadas = [{"nombre": recursion.nombre, "descripcion": recursion.resumen} for recursion in recursiones]
        
        return len(recursiones_crackeadas), recursiones_crackeadas
    
    def get_recursion(self, nombre) -> TheStrange_recursion|None:
        recursion = self.recursion_dao.get_recursion_by_name(nombre)
        
        return recursion