from pydantic import BaseModel

class FichaCreateRequest(BaseModel):
    nombre: str
    clase: str
    experiencia: int
    esfuerzo: int
    limiteDispositivos: int
    recuperacion: str
    trasfondo: bytes
    descriptor: str
    vinculoDescriptor: str