package com.hugman.dawn.api.creator;

import com.hugman.dawn.api.util.CreatorBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.util.registry.Registry;

public class EntityCreator extends Creator<EntityType<? extends Entity>> {
	protected final EntityType<? extends Entity> baseEntityType;
	protected final DefaultAttributeContainer.Builder attributeBuilder;

	private EntityCreator(String name, EntityType<? extends Entity> baseEntityType, DefaultAttributeContainer.Builder attributeBuilder) {
		super(name);
		this.baseEntityType = baseEntityType;
		this.attributeBuilder = attributeBuilder;
	}

	@Override
	public EntityType<? extends Entity> register(ModData modData) {
		value = Registry.register(Registry.ENTITY_TYPE, modData.id(name), baseEntityType);
		if(attributeBuilder != null) {
			FabricDefaultAttributeRegistry.register((EntityType<? extends LivingEntity>) value, attributeBuilder);
		}
		return value;
	}

	public static class Builder implements CreatorBuilder {
		protected final String name;
		protected final EntityType<? extends Entity> baseEntityType;
		protected DefaultAttributeContainer.Builder attributeBuilder;

		/**
		 * Creates an entity type.
		 *
		 * @param name           The name of the entity type.
		 * @param baseEntityType The entity type itself.
		 */
		public Builder(String name, EntityType<? extends Entity> baseEntityType) {
			this.name = name;
			this.baseEntityType = baseEntityType;
		}

		public Builder attributes(DefaultAttributeContainer.Builder attributeBuilder) {
			this.attributeBuilder = attributeBuilder;
			return this;
		}

		public EntityCreator build() {
			return new EntityCreator(this.name, this.baseEntityType, this.attributeBuilder);
		}
	}
}
