from app.model.sql.dao.TheStrange.creatura import TheStrange_creaturador

class TheStrange_creatura_creaturador:
    def __init__(self):
        self.EL_CREATURADOR = TheStrange_creaturador()
        
    def get_all_creaturas(self) -> tuple[int, list[dict]]:
        creaturas = self.EL_CREATURADOR.get_creatura_list()
        
        creaturados = [creatura.nombre for creatura in creaturas]
        
        return len(creaturados), creaturados
    
    def get_all_creaturas_for_recursion(self, recursion_name: str) -> tuple[int, list[dict]]:
        creaturas = self.EL_CREATURADOR.get_creatura_by_recursion(recursion_name=recursion_name)
        
        creaturados = [creatura.nombre for creatura in creaturas]
        
        return len(creaturados), creaturados
    
    def detallame_la_creatura_papa(self, creatura_name: str) -> dict:
        infojobs = self.EL_CREATURADOR.get_creatura(creatura_name=creatura_name)
        
        if(infojobs == None):
            raise ValueError(f"No CREATURA named {creatura_name} has graced us with its pressence")
        
        return infojobs.to_json()