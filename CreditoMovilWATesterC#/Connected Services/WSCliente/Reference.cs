﻿//------------------------------------------------------------------------------
// <auto-generated>
//     Este código fue generado por una herramienta.
//     Versión de runtime:4.0.30319.42000
//
//     Los cambios en este archivo podrían causar un comportamiento incorrecto y se perderán si
//     se vuelve a generar el código.
// </auto-generated>
//------------------------------------------------------------------------------

namespace CreditoMovilWATesterC_.WSCliente {
    
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ServiceModel.ServiceContractAttribute(Namespace="https://services.creditomovil.pucp.edu.pe", ConfigurationName="WSCliente.ClienteWS")]
    public interface ClienteWS {
        
        // CODEGEN: El parámetro 'return' requiere información adicional de esquema que no se puede capturar con el modo de parámetros. El atributo específico es 'System.Xml.Serialization.XmlElementAttribute'.
        [System.ServiceModel.OperationContractAttribute(Action="https://services.creditomovil.pucp.edu.pe/ClienteWS/insertarClienteRequest", ReplyAction="https://services.creditomovil.pucp.edu.pe/ClienteWS/insertarClienteResponse")]
        [System.ServiceModel.XmlSerializerFormatAttribute(SupportFaults=true)]
        [System.ServiceModel.ServiceKnownTypeAttribute(typeof(usuario))]
        [return: System.ServiceModel.MessageParameterAttribute(Name="return")]
        CreditoMovilWATesterC_.WSCliente.insertarClienteResponse insertarCliente(CreditoMovilWATesterC_.WSCliente.insertarClienteRequest request);
        
        [System.ServiceModel.OperationContractAttribute(Action="https://services.creditomovil.pucp.edu.pe/ClienteWS/insertarClienteRequest", ReplyAction="https://services.creditomovil.pucp.edu.pe/ClienteWS/insertarClienteResponse")]
        System.Threading.Tasks.Task<CreditoMovilWATesterC_.WSCliente.insertarClienteResponse> insertarClienteAsync(CreditoMovilWATesterC_.WSCliente.insertarClienteRequest request);
        
        // CODEGEN: El parámetro 'return' requiere información adicional de esquema que no se puede capturar con el modo de parámetros. El atributo específico es 'System.Xml.Serialization.XmlElementAttribute'.
        [System.ServiceModel.OperationContractAttribute(Action="https://services.creditomovil.pucp.edu.pe/ClienteWS/obtenerClientePorIdRequest", ReplyAction="https://services.creditomovil.pucp.edu.pe/ClienteWS/obtenerClientePorIdResponse")]
        [System.ServiceModel.XmlSerializerFormatAttribute(SupportFaults=true)]
        [System.ServiceModel.ServiceKnownTypeAttribute(typeof(usuario))]
        [return: System.ServiceModel.MessageParameterAttribute(Name="return")]
        CreditoMovilWATesterC_.WSCliente.obtenerClientePorIdResponse obtenerClientePorId(CreditoMovilWATesterC_.WSCliente.obtenerClientePorIdRequest request);
        
        [System.ServiceModel.OperationContractAttribute(Action="https://services.creditomovil.pucp.edu.pe/ClienteWS/obtenerClientePorIdRequest", ReplyAction="https://services.creditomovil.pucp.edu.pe/ClienteWS/obtenerClientePorIdResponse")]
        System.Threading.Tasks.Task<CreditoMovilWATesterC_.WSCliente.obtenerClientePorIdResponse> obtenerClientePorIdAsync(CreditoMovilWATesterC_.WSCliente.obtenerClientePorIdRequest request);
        
        // CODEGEN: El parámetro 'return' requiere información adicional de esquema que no se puede capturar con el modo de parámetros. El atributo específico es 'System.Xml.Serialization.XmlElementAttribute'.
        [System.ServiceModel.OperationContractAttribute(Action="https://services.creditomovil.pucp.edu.pe/ClienteWS/eliminarClienteRequest", ReplyAction="https://services.creditomovil.pucp.edu.pe/ClienteWS/eliminarClienteResponse")]
        [System.ServiceModel.XmlSerializerFormatAttribute(SupportFaults=true)]
        [System.ServiceModel.ServiceKnownTypeAttribute(typeof(usuario))]
        [return: System.ServiceModel.MessageParameterAttribute(Name="return")]
        CreditoMovilWATesterC_.WSCliente.eliminarClienteResponse eliminarCliente(CreditoMovilWATesterC_.WSCliente.eliminarClienteRequest request);
        
        [System.ServiceModel.OperationContractAttribute(Action="https://services.creditomovil.pucp.edu.pe/ClienteWS/eliminarClienteRequest", ReplyAction="https://services.creditomovil.pucp.edu.pe/ClienteWS/eliminarClienteResponse")]
        System.Threading.Tasks.Task<CreditoMovilWATesterC_.WSCliente.eliminarClienteResponse> eliminarClienteAsync(CreditoMovilWATesterC_.WSCliente.eliminarClienteRequest request);
        
        // CODEGEN: El parámetro 'return' requiere información adicional de esquema que no se puede capturar con el modo de parámetros. El atributo específico es 'System.Xml.Serialization.XmlElementAttribute'.
        [System.ServiceModel.OperationContractAttribute(Action="https://services.creditomovil.pucp.edu.pe/ClienteWS/listarClientesRequest", ReplyAction="https://services.creditomovil.pucp.edu.pe/ClienteWS/listarClientesResponse")]
        [System.ServiceModel.XmlSerializerFormatAttribute(SupportFaults=true)]
        [System.ServiceModel.ServiceKnownTypeAttribute(typeof(usuario))]
        [return: System.ServiceModel.MessageParameterAttribute(Name="return")]
        CreditoMovilWATesterC_.WSCliente.listarClientesResponse listarClientes(CreditoMovilWATesterC_.WSCliente.listarClientesRequest request);
        
        [System.ServiceModel.OperationContractAttribute(Action="https://services.creditomovil.pucp.edu.pe/ClienteWS/listarClientesRequest", ReplyAction="https://services.creditomovil.pucp.edu.pe/ClienteWS/listarClientesResponse")]
        System.Threading.Tasks.Task<CreditoMovilWATesterC_.WSCliente.listarClientesResponse> listarClientesAsync(CreditoMovilWATesterC_.WSCliente.listarClientesRequest request);
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Xml", "4.8.9037.0")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="https://services.creditomovil.pucp.edu.pe")]
    public partial class cliente : usuario {
        
        private string codigoClienteField;
        
        private string direccionField;
        
        private string emailField;
        
        private string telefonoField;
        
        private string tipoClienteField;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
        public string codigoCliente {
            get {
                return this.codigoClienteField;
            }
            set {
                this.codigoClienteField = value;
                this.RaisePropertyChanged("codigoCliente");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=1)]
        public string direccion {
            get {
                return this.direccionField;
            }
            set {
                this.direccionField = value;
                this.RaisePropertyChanged("direccion");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=2)]
        public string email {
            get {
                return this.emailField;
            }
            set {
                this.emailField = value;
                this.RaisePropertyChanged("email");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=3)]
        public string telefono {
            get {
                return this.telefonoField;
            }
            set {
                this.telefonoField = value;
                this.RaisePropertyChanged("telefono");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=4)]
        public string tipoCliente {
            get {
                return this.tipoClienteField;
            }
            set {
                this.tipoClienteField = value;
                this.RaisePropertyChanged("tipoCliente");
            }
        }
    }
    
    /// <remarks/>
    [System.Xml.Serialization.XmlIncludeAttribute(typeof(cliente))]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Xml", "4.8.9037.0")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="https://services.creditomovil.pucp.edu.pe")]
    public abstract partial class usuario : object, System.ComponentModel.INotifyPropertyChanged {
        
        private bool activoField;
        
        private string apMaternoField;
        
        private string apPaternoField;
        
        private string contrasenhaField;
        
        private System.DateTime fechaField;
        
        private bool fechaFieldSpecified;
        
        private System.DateTime fechaVencimientoField;
        
        private bool fechaVencimientoFieldSpecified;
        
        private int idUsuarioField;
        
        private string nombreField;
        
        private System.DateTime ultimoLogueoField;
        
        private bool ultimoLogueoFieldSpecified;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=0)]
        public bool activo {
            get {
                return this.activoField;
            }
            set {
                this.activoField = value;
                this.RaisePropertyChanged("activo");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=1)]
        public string apMaterno {
            get {
                return this.apMaternoField;
            }
            set {
                this.apMaternoField = value;
                this.RaisePropertyChanged("apMaterno");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=2)]
        public string apPaterno {
            get {
                return this.apPaternoField;
            }
            set {
                this.apPaternoField = value;
                this.RaisePropertyChanged("apPaterno");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=3)]
        public string contrasenha {
            get {
                return this.contrasenhaField;
            }
            set {
                this.contrasenhaField = value;
                this.RaisePropertyChanged("contrasenha");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=4)]
        public System.DateTime fecha {
            get {
                return this.fechaField;
            }
            set {
                this.fechaField = value;
                this.RaisePropertyChanged("fecha");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlIgnoreAttribute()]
        public bool fechaSpecified {
            get {
                return this.fechaFieldSpecified;
            }
            set {
                this.fechaFieldSpecified = value;
                this.RaisePropertyChanged("fechaSpecified");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=5)]
        public System.DateTime fechaVencimiento {
            get {
                return this.fechaVencimientoField;
            }
            set {
                this.fechaVencimientoField = value;
                this.RaisePropertyChanged("fechaVencimiento");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlIgnoreAttribute()]
        public bool fechaVencimientoSpecified {
            get {
                return this.fechaVencimientoFieldSpecified;
            }
            set {
                this.fechaVencimientoFieldSpecified = value;
                this.RaisePropertyChanged("fechaVencimientoSpecified");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=6)]
        public int idUsuario {
            get {
                return this.idUsuarioField;
            }
            set {
                this.idUsuarioField = value;
                this.RaisePropertyChanged("idUsuario");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=7)]
        public string nombre {
            get {
                return this.nombreField;
            }
            set {
                this.nombreField = value;
                this.RaisePropertyChanged("nombre");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified, Order=8)]
        public System.DateTime ultimoLogueo {
            get {
                return this.ultimoLogueoField;
            }
            set {
                this.ultimoLogueoField = value;
                this.RaisePropertyChanged("ultimoLogueo");
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlIgnoreAttribute()]
        public bool ultimoLogueoSpecified {
            get {
                return this.ultimoLogueoFieldSpecified;
            }
            set {
                this.ultimoLogueoFieldSpecified = value;
                this.RaisePropertyChanged("ultimoLogueoSpecified");
            }
        }
        
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected void RaisePropertyChanged(string propertyName) {
            System.ComponentModel.PropertyChangedEventHandler propertyChanged = this.PropertyChanged;
            if ((propertyChanged != null)) {
                propertyChanged(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
            }
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="insertarCliente", WrapperNamespace="https://services.creditomovil.pucp.edu.pe", IsWrapped=true)]
    public partial class insertarClienteRequest {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="https://services.creditomovil.pucp.edu.pe", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public CreditoMovilWATesterC_.WSCliente.cliente cliente;
        
        public insertarClienteRequest() {
        }
        
        public insertarClienteRequest(CreditoMovilWATesterC_.WSCliente.cliente cliente) {
            this.cliente = cliente;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="insertarClienteResponse", WrapperNamespace="https://services.creditomovil.pucp.edu.pe", IsWrapped=true)]
    public partial class insertarClienteResponse {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="https://services.creditomovil.pucp.edu.pe", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public bool @return;
        
        public insertarClienteResponse() {
        }
        
        public insertarClienteResponse(bool @return) {
            this.@return = @return;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="obtenerClientePorId", WrapperNamespace="https://services.creditomovil.pucp.edu.pe", IsWrapped=true)]
    public partial class obtenerClientePorIdRequest {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="https://services.creditomovil.pucp.edu.pe", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public string idCliente;
        
        public obtenerClientePorIdRequest() {
        }
        
        public obtenerClientePorIdRequest(string idCliente) {
            this.idCliente = idCliente;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="obtenerClientePorIdResponse", WrapperNamespace="https://services.creditomovil.pucp.edu.pe", IsWrapped=true)]
    public partial class obtenerClientePorIdResponse {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="https://services.creditomovil.pucp.edu.pe", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public CreditoMovilWATesterC_.WSCliente.cliente @return;
        
        public obtenerClientePorIdResponse() {
        }
        
        public obtenerClientePorIdResponse(CreditoMovilWATesterC_.WSCliente.cliente @return) {
            this.@return = @return;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="eliminarCliente", WrapperNamespace="https://services.creditomovil.pucp.edu.pe", IsWrapped=true)]
    public partial class eliminarClienteRequest {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="https://services.creditomovil.pucp.edu.pe", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public string idCliente;
        
        public eliminarClienteRequest() {
        }
        
        public eliminarClienteRequest(string idCliente) {
            this.idCliente = idCliente;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="eliminarClienteResponse", WrapperNamespace="https://services.creditomovil.pucp.edu.pe", IsWrapped=true)]
    public partial class eliminarClienteResponse {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="https://services.creditomovil.pucp.edu.pe", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public bool @return;
        
        public eliminarClienteResponse() {
        }
        
        public eliminarClienteResponse(bool @return) {
            this.@return = @return;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="listarClientes", WrapperNamespace="https://services.creditomovil.pucp.edu.pe", IsWrapped=true)]
    public partial class listarClientesRequest {
        
        public listarClientesRequest() {
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(WrapperName="listarClientesResponse", WrapperNamespace="https://services.creditomovil.pucp.edu.pe", IsWrapped=true)]
    public partial class listarClientesResponse {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Namespace="https://services.creditomovil.pucp.edu.pe", Order=0)]
        [System.Xml.Serialization.XmlElementAttribute("return", Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public CreditoMovilWATesterC_.WSCliente.cliente[] @return;
        
        public listarClientesResponse() {
        }
        
        public listarClientesResponse(CreditoMovilWATesterC_.WSCliente.cliente[] @return) {
            this.@return = @return;
        }
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public interface ClienteWSChannel : CreditoMovilWATesterC_.WSCliente.ClienteWS, System.ServiceModel.IClientChannel {
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public partial class ClienteWSClient : System.ServiceModel.ClientBase<CreditoMovilWATesterC_.WSCliente.ClienteWS>, CreditoMovilWATesterC_.WSCliente.ClienteWS {
        
        public ClienteWSClient() {
        }
        
        public ClienteWSClient(string endpointConfigurationName) : 
                base(endpointConfigurationName) {
        }
        
        public ClienteWSClient(string endpointConfigurationName, string remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public ClienteWSClient(string endpointConfigurationName, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public ClienteWSClient(System.ServiceModel.Channels.Binding binding, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(binding, remoteAddress) {
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        CreditoMovilWATesterC_.WSCliente.insertarClienteResponse CreditoMovilWATesterC_.WSCliente.ClienteWS.insertarCliente(CreditoMovilWATesterC_.WSCliente.insertarClienteRequest request) {
            return base.Channel.insertarCliente(request);
        }
        
        public bool insertarCliente(CreditoMovilWATesterC_.WSCliente.cliente cliente) {
            CreditoMovilWATesterC_.WSCliente.insertarClienteRequest inValue = new CreditoMovilWATesterC_.WSCliente.insertarClienteRequest();
            inValue.cliente = cliente;
            CreditoMovilWATesterC_.WSCliente.insertarClienteResponse retVal = ((CreditoMovilWATesterC_.WSCliente.ClienteWS)(this)).insertarCliente(inValue);
            return retVal.@return;
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task<CreditoMovilWATesterC_.WSCliente.insertarClienteResponse> CreditoMovilWATesterC_.WSCliente.ClienteWS.insertarClienteAsync(CreditoMovilWATesterC_.WSCliente.insertarClienteRequest request) {
            return base.Channel.insertarClienteAsync(request);
        }
        
        public System.Threading.Tasks.Task<CreditoMovilWATesterC_.WSCliente.insertarClienteResponse> insertarClienteAsync(CreditoMovilWATesterC_.WSCliente.cliente cliente) {
            CreditoMovilWATesterC_.WSCliente.insertarClienteRequest inValue = new CreditoMovilWATesterC_.WSCliente.insertarClienteRequest();
            inValue.cliente = cliente;
            return ((CreditoMovilWATesterC_.WSCliente.ClienteWS)(this)).insertarClienteAsync(inValue);
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        CreditoMovilWATesterC_.WSCliente.obtenerClientePorIdResponse CreditoMovilWATesterC_.WSCliente.ClienteWS.obtenerClientePorId(CreditoMovilWATesterC_.WSCliente.obtenerClientePorIdRequest request) {
            return base.Channel.obtenerClientePorId(request);
        }
        
        public CreditoMovilWATesterC_.WSCliente.cliente obtenerClientePorId(string idCliente) {
            CreditoMovilWATesterC_.WSCliente.obtenerClientePorIdRequest inValue = new CreditoMovilWATesterC_.WSCliente.obtenerClientePorIdRequest();
            inValue.idCliente = idCliente;
            CreditoMovilWATesterC_.WSCliente.obtenerClientePorIdResponse retVal = ((CreditoMovilWATesterC_.WSCliente.ClienteWS)(this)).obtenerClientePorId(inValue);
            return retVal.@return;
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task<CreditoMovilWATesterC_.WSCliente.obtenerClientePorIdResponse> CreditoMovilWATesterC_.WSCliente.ClienteWS.obtenerClientePorIdAsync(CreditoMovilWATesterC_.WSCliente.obtenerClientePorIdRequest request) {
            return base.Channel.obtenerClientePorIdAsync(request);
        }
        
        public System.Threading.Tasks.Task<CreditoMovilWATesterC_.WSCliente.obtenerClientePorIdResponse> obtenerClientePorIdAsync(string idCliente) {
            CreditoMovilWATesterC_.WSCliente.obtenerClientePorIdRequest inValue = new CreditoMovilWATesterC_.WSCliente.obtenerClientePorIdRequest();
            inValue.idCliente = idCliente;
            return ((CreditoMovilWATesterC_.WSCliente.ClienteWS)(this)).obtenerClientePorIdAsync(inValue);
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        CreditoMovilWATesterC_.WSCliente.eliminarClienteResponse CreditoMovilWATesterC_.WSCliente.ClienteWS.eliminarCliente(CreditoMovilWATesterC_.WSCliente.eliminarClienteRequest request) {
            return base.Channel.eliminarCliente(request);
        }
        
        public bool eliminarCliente(string idCliente) {
            CreditoMovilWATesterC_.WSCliente.eliminarClienteRequest inValue = new CreditoMovilWATesterC_.WSCliente.eliminarClienteRequest();
            inValue.idCliente = idCliente;
            CreditoMovilWATesterC_.WSCliente.eliminarClienteResponse retVal = ((CreditoMovilWATesterC_.WSCliente.ClienteWS)(this)).eliminarCliente(inValue);
            return retVal.@return;
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task<CreditoMovilWATesterC_.WSCliente.eliminarClienteResponse> CreditoMovilWATesterC_.WSCliente.ClienteWS.eliminarClienteAsync(CreditoMovilWATesterC_.WSCliente.eliminarClienteRequest request) {
            return base.Channel.eliminarClienteAsync(request);
        }
        
        public System.Threading.Tasks.Task<CreditoMovilWATesterC_.WSCliente.eliminarClienteResponse> eliminarClienteAsync(string idCliente) {
            CreditoMovilWATesterC_.WSCliente.eliminarClienteRequest inValue = new CreditoMovilWATesterC_.WSCliente.eliminarClienteRequest();
            inValue.idCliente = idCliente;
            return ((CreditoMovilWATesterC_.WSCliente.ClienteWS)(this)).eliminarClienteAsync(inValue);
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        CreditoMovilWATesterC_.WSCliente.listarClientesResponse CreditoMovilWATesterC_.WSCliente.ClienteWS.listarClientes(CreditoMovilWATesterC_.WSCliente.listarClientesRequest request) {
            return base.Channel.listarClientes(request);
        }
        
        public CreditoMovilWATesterC_.WSCliente.cliente[] listarClientes() {
            CreditoMovilWATesterC_.WSCliente.listarClientesRequest inValue = new CreditoMovilWATesterC_.WSCliente.listarClientesRequest();
            CreditoMovilWATesterC_.WSCliente.listarClientesResponse retVal = ((CreditoMovilWATesterC_.WSCliente.ClienteWS)(this)).listarClientes(inValue);
            return retVal.@return;
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task<CreditoMovilWATesterC_.WSCliente.listarClientesResponse> CreditoMovilWATesterC_.WSCliente.ClienteWS.listarClientesAsync(CreditoMovilWATesterC_.WSCliente.listarClientesRequest request) {
            return base.Channel.listarClientesAsync(request);
        }
        
        public System.Threading.Tasks.Task<CreditoMovilWATesterC_.WSCliente.listarClientesResponse> listarClientesAsync() {
            CreditoMovilWATesterC_.WSCliente.listarClientesRequest inValue = new CreditoMovilWATesterC_.WSCliente.listarClientesRequest();
            return ((CreditoMovilWATesterC_.WSCliente.ClienteWS)(this)).listarClientesAsync(inValue);
        }
    }
}
