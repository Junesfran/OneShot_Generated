from app import db

class TheStrange_descriptorRecursion(db.Model):
    __tablename__ = "DescriptorRecursion"
    __bind_key__ = "the_strange"

    descriptor_nombre = db.Column(db.String(45), db.ForeignKey("Descriptor.nombre"), primary_key=True)
    recursion_nombre = db.Column(db.String(45), db.ForeignKey("Recursion.nombre"), primary_key=True)
    mejora = db.Column(db.Text)

    def __init__(self, descriptor_nombre: str, recursion_nombre: str, mejora: str = None):
        self.descriptor_nombre = descriptor_nombre
        self.recursion_nombre = recursion_nombre
        self.mejora = mejora

    def to_json(self):
        return {
            "descriptor_nombre": self.descriptor_nombre,
            "recursion_nombre": self.recursion_nombre,
            "mejora": self.mejora
        }