from pydantic import BaseModel

class Dispositivo(BaseModel):
    nombre: str
    
class CapacidadesEspeciales(BaseModel):
    nombre: str
    
class Equipo(BaseModel):
    nombre: str
    
class Competencias(BaseModel):
    nombre: str
    especializado: bool
class FichaCreateRequest(BaseModel):
	Manual_id: str
	accion: bool
	armadura: int
	aumentarC: bool
	clase: str
	competenciaH: bool
	descriptor: str
	dinero: int
	esfuerzo: int
	esfuerzoExtra: bool
	experiencia: int
	hora: bool
	horas: bool
	inteligenciaAct: int
	limiteDispositivos: int
	maxDispositivos: int
	minutos: bool
	nombre: str
	otros: bool
	perfeccion: bool
	rango: int
	rasgo: str
	recuperacion: str
	recursion: str
	reservaInteligenciaMax: int
	reservaVelocidadMax: int
	reservaVigorMax: int
	tipo: str
	trasfondo: bytes
	velocidadAct: int
	ventajaInteligenciaMax: int
	ventajaVelocidadMax: int
	ventajaVigorMax: int
	vigorAct: int
	vinculoDescriptor: str
	dispositivos: list[Dispositivo] | None
	equipo: list[Equipo] | None
	capacidadesEspeciales: list[CapacidadesEspeciales] | None
	competencias: list[Competencias] | None