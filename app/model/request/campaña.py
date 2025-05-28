from pydantic import BaseModel

class CampañaRequestModel(BaseModel):
    nombre: str
    descripcion: str
    manual: str
    idImagen: int
    contrasenyn: str