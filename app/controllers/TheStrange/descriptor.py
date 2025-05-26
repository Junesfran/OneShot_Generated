from app.model.sql.dao.TheStrange.descriptor import TheStrange_descriptorDAO

class TheStrange_descriptor_controller:
    def __init__(self):
        self.descriptor_dao = TheStrange_descriptorDAO()
        
    def get_all_descriptors(self) -> tuple[int, list[dict]]:
        descriptors = self.descriptor_dao.get_descriptor_list()
        
        based_descriptors = [descriptor.to_json() for descriptor in descriptors]
        
        return len(based_descriptors), based_descriptors
    
    def get_spicy_descriptor_for(self, recursion_name: str) -> dict:
        descriptor = self.descriptor_dao.get_spicy_descriptor_for_recursion(recursion_name=recursion_name)
        
        if(descriptor == None):
            raise ValueError(f"{recursion_name} innards are not up to snuff in the spice department")
        
        return descriptor.to_json()