// Reproduit le comportement LinearLayout vertical + layout_weight
// (activity_login.xml / activity_register.xml) : les enfants sans poids prennent
// leur taille naturelle, l'espace restant est partagé au prorata des poids.
import SwiftUI

/// Poids Android (`layout_weight`) d'un enfant de `AuthWeightedColumn`.
/// `margin` = marges verticales déjà appliquées en padding sur l'enfant.
struct AuthWeightKey: LayoutValueKey {
    static let defaultValue: (weight: CGFloat, margin: CGFloat)? = nil
}

extension View {
    func authWeight(_ weight: CGFloat, margin: CGFloat = 0) -> some View {
        layoutValue(key: AuthWeightKey.self, value: (weight, margin))
    }
}

struct AuthWeightedColumn: Layout {
    func sizeThatFits(proposal: ProposedViewSize, subviews: Subviews, cache: inout ()) -> CGSize {
        CGSize(width: proposal.width ?? 0, height: proposal.height ?? 0)
    }

    func placeSubviews(in bounds: CGRect, proposal: ProposedViewSize,
                       subviews: Subviews, cache: inout ()) {
        let sizes = childHeights(for: bounds.size, subviews: subviews)
        var y = bounds.minY
        for (index, subview) in subviews.enumerated() {
            let h = sizes[index]
            subview.place(at: CGPoint(x: bounds.minX, y: y), anchor: .topLeading,
                          proposal: ProposedViewSize(width: bounds.width, height: h))
            y += h
        }
    }

    private func childHeights(for size: CGSize, subviews: Subviews) -> [CGFloat] {
        var fixed: CGFloat = 0
        var totalWeight: CGFloat = 0
        var result = [CGFloat](repeating: 0, count: subviews.count)
        for (index, subview) in subviews.enumerated() {
            if let w = subview[AuthWeightKey.self] {
                totalWeight += w.weight
                fixed += w.margin
            } else {
                let h = subview.sizeThatFits(ProposedViewSize(width: size.width, height: nil)).height
                result[index] = h
                fixed += h
            }
        }
        let remaining = max(0, size.height - fixed)
        for (index, subview) in subviews.enumerated() {
            if let w = subview[AuthWeightKey.self], totalWeight > 0 {
                result[index] = remaining * w.weight / totalWeight + w.margin
            }
        }
        return result
    }
}
