from app import db

from app.model.sql.model.ficha import Ficha_TheStrange
from app.model.sql.model.capacidadesEspeciales import TheStrange_CapacidadesEspeciales
from app.model.sql.model.competencias import TheStrange_Competencias
from app.model.sql.model.dispositivo import TheStrange_Dispositivo
from app.model.sql.model.equipo import TheStrange_Equipo
from app.model.request.ficha import FichaCreateRequest

class FichaDAO:
    def __init__(self):
        self.session = db.session
    
    def yeet(self, ficha_id):
        ficha = self.session.query(Ficha_TheStrange).filter_by(id=ficha_id).first()
        if(ficha == None):
            raise ValueError("non")
        
        try:
            self.session.delete(ficha)
            self.session.commit()
        except Exception as e:
            self.session.rollback()
            raise ValueError("non pero peor, bastante")
    
    def get_user_fichas(self, user_id):
        try:
            return self.session.query(Ficha_TheStrange).filter_by(Usuario_idUsuario=user_id).all()
        except Exception as e:
            print(e)
    
    def get_by_id(self, user_id, ficha_id):
        return (self.session.query(Ficha_TheStrange).filter_by(Usuario_idUsuario=user_id).filter_by(id=ficha_id).first(),
                self.session.query(TheStrange_CapacidadesEspeciales).filter_by(Ficha_TheStrange_id=ficha_id).all(),
                self.session.query(TheStrange_Competencias).filter_by(Ficha_TheStrange_id=ficha_id).all(),
                self.session.query(TheStrange_Dispositivo).filter_by(Ficha_TheStrange_id=ficha_id).all(),
                self.session.query(TheStrange_Equipo).filter_by(Ficha_TheStrange_id=ficha_id).all())
    
    def create(self, id_usuario: str,  ficha: FichaCreateRequest):
        novo_du_fich: Ficha_TheStrange = Ficha_TheStrange(
            nombre=ficha.nombre,
            clase=ficha.clase,
            descriptor=ficha.descriptor,
            esfuerzo=ficha.esfuerzo,
            experiencia=ficha.experiencia,
            limiteDispositivos=ficha.limiteDispositivos,
            recuperacion=ficha.recuperacion,
            trasfondo=ficha.trasfondo,
            vinculoDescriptor=ficha.vinculoDescriptor,
            Manual_id="the_stange",
            Usuario_idUsuario=id_usuario,
            accion=ficha.accion,
            armadura=ficha.armadura,
            aumentarC=ficha.aumentarC,
            competenciaH=ficha.competenciaH,
            dinero=ficha.dinero,
            esfuerzoExtra=ficha.esfuerzoExtra,
            hora=ficha.hora,
            horas=ficha.horas,
            inteligenciaAct=ficha.inteligenciaAct,
            maxDispositivos=ficha.maxDispositivos,
            minutos=ficha.minutos,
            otros=ficha.otros,
            perfeccion=ficha.perfeccion,
            rango=ficha.rango,
            rasgo=ficha.rasgo,
            recursion=ficha.recursion,
            reservaInteligenciaMax=ficha.reservaInteligenciaMax,
            reservaVelocidadMax=ficha.reservaVelocidadMax,
            reservaVigorMax=ficha.reservaVigorMax,
            tipo=ficha.tipo,
            velocidadAct=ficha.velocidadAct,
            ventajaInteligenciaMax=ficha.ventajaInteligenciaMax,
            ventajaVelocidadMax=ficha.ventajaVelocidadMax,
            ventajaVigorAct=ficha.ventajaVigorMax,
            ventajaVigorMax=ficha.ventajaVigorMax,
            vigorAct=ficha.vigorAct
            
        )
        try:
            self.session.add(novo_du_fich)
            # el enano seguia con la mano en el nabo
            # y eso era raro
            # porque recien estaba operado
            self.session.flush() 
            
            if(ficha.dispositivos):
                if(len(ficha.dispositivos) > 0):
                    for dispositivo in ficha.dispositivos:
                        vicio = TheStrange_Dispositivo(nombre=dispositivo.nombre, Ficha_TheStrange_id=novo_du_fich.id)
                        self.session.add(vicio)
            
            if(ficha.competencias):
                if(len(ficha.competencias) > 0):
                    for competencia in ficha.competencias:
                        vicio = TheStrange_Competencias(nombre=competencia.nombre, especializado=competencia.especializado, Ficha_TheStrange_id=novo_du_fich.id)
                        self.session.add(vicio)
            
            if(ficha.equipo):
                if(len(ficha.equipo) > 0):
                    for equipo in ficha.equipo:
                        vicio = TheStrange_Equipo(nombre=equipo.nombre, Ficha_TheStrange_id=novo_du_fich.id)
                        self.session.add(vicio)
            
            if(ficha.capacidadesEspeciales):
                if(len(ficha.capacidadesEspeciales) > 0):
                    for capacidad in ficha.capacidadesEspeciales:
                        vicio = TheStrange_CapacidadesEspeciales(nombre=capacidad.nombre, Ficha_TheStrange_id=novo_du_fich.id)
                        self.session.add(vicio)
            
            self.session.commit()
        except Exception as e:
            self.session.rollback()
        return novo_du_fich