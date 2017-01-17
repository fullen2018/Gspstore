	private ${entityName}Entity ${entityName?uncap_first}Entity;
	private Set<${entityName}Entity> ${entityName?uncap_first}Entitys = new HashSet<${entityName}Entity>(0);
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CPID")
	public ${entityName}Entity get${entityName}Entity() {
		return ${entityName?uncap_first}Entity;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "${entityName?uncap_first}Entity")
	public Set<${entityName}Entity> get${entityName}Entitys() {
		return ${entityName?uncap_first}Entitys;
	}

	public void set${entityName}Entity(${entityName}Entity ${entityName?uncap_first}Entity) {
		this.${entityName?uncap_first}Entity = ${entityName?uncap_first}Entity;
	}

	public void set${entityName}Entitys(Set<${entityName}Entity> ${entityName?uncap_first}Entitys) {
		this.${entityName?uncap_first}Entitys = ${entityName?uncap_first}Entitys;
	}
